import { Personne } from './personne.model';

export interface Technicien extends Personne {
  specialite?: string;
}
