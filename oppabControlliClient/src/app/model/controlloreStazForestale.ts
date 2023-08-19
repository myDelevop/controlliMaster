import { Controllore } from 'src/app/model/controllore';
import { StazioneForestale } from './stazioneForestale';

export class ControlloreStazioneForestale {
  id: number;
  idContrStazFor: number;
  controllore: Controllore;
  stazioneForestale: StazioneForestale;
  annoValiditaInizio: number;
  annoValiditaFine: number;
  note: string;
}
