package br.edu.ifsp.ingresso.dao.usuario;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import br.edu.ifsp.ingresso.models.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {
	
	private Session currentSession;
	
	private Transaction currentTransaction;
	
	public UsuarioDAOImpl() {
		
	}
	
	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	
	private static SessionFactory getSessionFactory() {
		        Configuration configuration = new Configuration().configure();
		        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		                .applySettings(configuration.getProperties());
		        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		        return sessionFactory;
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	
	public Session openCurrentSessionWithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionWithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	@Override
	public void persist(Usuario user) {
		getCurrentSession().save(user);
	}
	
	@Override
	public void update(Usuario user) {
		getCurrentSession().update(user);
	}
	
	@Override
	public Usuario findById(long id) {
		Usuario user = (Usuario) getCurrentSession().get(Usuario.class, id);
		return user;
	}
	
	
	@Override
	public void delete (Usuario user) {
		getCurrentSession().delete(user);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findAll(){
		List<Usuario> users = (List<Usuario>) getCurrentSession().createQuery("FROM USUARIO").list();
		return users;
	}
	
	@Override
	public void deleteAll() {
		List<Usuario> users = findAll();
		for (Usuario user : users) {
			delete(user);
		}
	}

	@Override
	public Usuario findByEmail(String email) {
		Usuario user = (Usuario) getCurrentSession().get(Usuario.class, email);
		return user;
	}
}
