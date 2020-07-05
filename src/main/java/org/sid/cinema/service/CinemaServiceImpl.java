package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.PlaceRepository;
import org.sid.cinema.dao.ProjectionRepository;
import org.sid.cinema.dao.SalleRepository;
import org.sid.cinema.dao.SeanceRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.dao.VilleRepository;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Place;
import org.sid.cinema.entities.Projection;
import org.sid.cinema.entities.Salle;
import org.sid.cinema.entities.Seance;
import org.sid.cinema.entities.Ticket;
import org.sid.cinema.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaServiceImpl implements ICinemaInitService {
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void initVilles() {
		Stream.of("Casablanca", "Fes", "Rabat", "Tanger").forEach(nameVille -> {
			Ville v = new Ville();
			v.setName(nameVille);
			villeRepository.save(v);
		});

	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v -> {
			Stream.of("MegaRama", "IMAX", "Founoun", "Chahrazad").forEach(name -> {
				Cinema c = new Cinema();
				c.setName(name);
				c.setNombreSalle(3 + (int) Math.random() * 7);
				c.setVille(v);
				cinemaRepository.save(c);
			});
		});
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema -> {
			for (int i = 0; i < cinema.getNombreSalle(); i++) {
				Salle salle = new Salle();
				salle.setName("Salle" + (i + 1));
				salle.setCinema(cinema);
				salle.setNombrePlaces(15 + (int) Math.random() * 20);
				salleRepository.save(salle);

			}
		});

	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle -> {
			for (int i = 0; i < salle.getNombrePlaces(); i++) {
				Place place = new Place();
				place.setSalle(salle);
				place.setNumero(i + 1);
				placeRepository.save(place);
			}

		});

	}

	@Override
	public void initSeances() {
		Stream.of("12:00", "15:00", "19:00", "22:00").forEach(s -> {

			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		;

	}

	@Override
	public void initCateories() {
		Stream.of("Action", "Drama", "Fiction", "Comedie").forEach(name -> {
			Categorie categorie = new Categorie();
			categorie.setName(name);
			categorieRepository.save(categorie);

		});

	}

	@Override
	public void initFilms() {
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("Game Of Thrones", "Viking", "Titanic").forEach(titre -> {
			Film film = new Film();
			film.setTitre(titre);
			film.setPhoto(titre.replaceAll(" ", "")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
	}

	@Override
	public void initProjections() {
		villeRepository.findAll().forEach(ville -> {
			ville.getCinemas().forEach(cinema -> {
				cinema.getSalles().forEach(salle -> {
					filmRepository.findAll().forEach(film -> {
						seanceRepository.findAll().forEach(seance -> {
							Projection projection = new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionRepository.save(projection);

						});
					});
				});
			});
		});

	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(projection -> {
			projection.getSalle().getPlaces().forEach(place -> {
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setProjection(projection);
				ticket.setPrix(projection.getPrix());
				ticket.setReserve(false);

				ticketRepository.save(ticket);
			});
		});

	}

}
