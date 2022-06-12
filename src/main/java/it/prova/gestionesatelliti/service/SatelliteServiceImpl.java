package it.prova.gestionesatelliti.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.model.StatoSatellite;
import it.prova.gestionesatelliti.repository.SatelliteRepository;

@Service
public class SatelliteServiceImpl implements SatelliteService {

	@Autowired
	SatelliteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Satellite> listAllElements() {

		return (List<Satellite>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Satellite caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Satellite satelliteInstance) {
		repository.save(satelliteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Satellite satelliteInstance) {
		repository.save(satelliteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Satellite satelliteInstance) {
		repository.delete(satelliteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Satellite> findByExample(Satellite example) {

		Specification<Satellite> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getDenominazione()))
				predicates.add(cb.like(cb.upper(root.get("denominazione")),
						"%" + example.getDenominazione().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(
						cb.like(cb.upper(root.get("codiceSatellite")), "%" + example.getCodice().toUpperCase() + "%"));

			if (example.getStatoSatellite() != null)
				predicates.add(cb.equal(root.get("statoSatellite"), example.getStatoSatellite()));

			if (example.getDataLancio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataLancio"), example.getDataLancio()));

			if (example.getDataRientro() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataRientro"), example.getDataRientro()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		return repository.findAll(specificationCriteria);
	}

	@Override
	public List<Satellite> lanciatiDaPiùDiDueAnni() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -2);
		Date dataDueAnniFa = calendar.getTime();
		return repository.findAllByStatoSatelliteAndDataLancioBefore(StatoSatellite.DISATTIVATO, dataDueAnniFa);
	}

	@Override
	public List<Satellite> disattivatiNonRientrati() {
		return repository.findAllByStatoSatelliteAndDataRientroNull(StatoSatellite.DISATTIVATO);
	}

	@Override
	public List<Satellite> fissiInOrbita10Anni() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -10);
		Date data10AnniFa = calendar.getTime();
		return repository.findAllByStatoSatelliteAndDataLancioBefore(StatoSatellite.FISSO, data10AnniFa);
	}

}
