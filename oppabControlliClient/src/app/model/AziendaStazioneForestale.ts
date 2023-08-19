import { Azienda } from './azienda';
import { Controllore } from 'src/app/model/controllore';
import { StazioneForestale } from './stazioneForestale';

export class AziendaStazioneForestale {
  id: number;
  idAziStazFor: number;
  azienda: Azienda;
  stazioneForestale: StazioneForestale;
  campagna: number;
  note: string;
}
