
import { Personne } from './personne.model';

export interface User extends Personne {
  fonction?: string;
  tickets?: any[];
}
