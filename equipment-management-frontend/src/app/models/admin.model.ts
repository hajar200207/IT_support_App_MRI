import { Personne } from './personne.model';

export interface Admin extends Personne {
  departement?: string;
}
