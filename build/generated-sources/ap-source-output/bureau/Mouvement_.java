package bureau;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mouvement.class)
public abstract class Mouvement_ {

	public static volatile SingularAttribute<Mouvement, UniteFonctionnelle> uf;
	public static volatile SingularAttribute<Mouvement, Lit> lit;
	public static volatile SingularAttribute<Mouvement, Integer> id_mouv;
	public static volatile SingularAttribute<Mouvement, Date> date_entree;
	public static volatile SingularAttribute<Mouvement, Date> date_sortie;
	public static volatile SingularAttribute<Mouvement, Admission> admission;

}

