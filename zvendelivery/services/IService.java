package zvendelivery.services;

import zvendelivery.entites.Produits;

import java.util.List;

public interface IService <T>{
     void ajouter(T t);
     void modifer(T t);

    void supprimer(int id);
    List<T> afficher();
    List<Produits> afficherbyrestaurant(T t);

}
