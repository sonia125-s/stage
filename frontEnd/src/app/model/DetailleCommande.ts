export interface DetailleCommandeDTO {
  id: string ;
  date_creation: Date;
  date_envoi: Date;
  lieu: string;
  prixTotal: number;
  ligneCommandeDTOList: LigneCommandeDtolist[];
}

export interface LigneCommandeDtolist {
  id: string;
  quantite: number;
  produitDTO: ProduitDTO;
}

export interface ProduitDTO {
  id: string;
  prix: number
  description: string
}
