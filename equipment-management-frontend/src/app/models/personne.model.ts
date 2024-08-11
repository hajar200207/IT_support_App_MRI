export interface Personne {
  id?: number;
  email: string;
  motDePasse: string;
  role?: Role;
  type: string;
  prenom?: string;
  nom?: string;
}
export enum Role {
  TECHNICIEN = 'ROLE_TECHNICIEN',
  USER = 'ROLE_USER',
  ADMIN = 'ROLE_ADMIN'
}
