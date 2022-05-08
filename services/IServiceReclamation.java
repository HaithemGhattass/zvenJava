/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zvendelivery.services;

import java.util.List;
import zvendelivery.entites.Reclamation;

/**
 *
 * @author mtar
 */
public interface IServiceReclamation {
    void ajouter(Reclamation r);
    void modifier( Reclamation r);
    void supprimer(int ID_Reclamation);
    List<Reclamation> afficher();
    List<Reclamation> recherche(int ID_Reclamation);
}
