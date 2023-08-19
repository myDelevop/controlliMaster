export class Utente {
  id: number;
  idUtente: number;
  nome: string;
  cognome: string;
  username: string;
  email: string;
  dominio: string;

  constructor(obj: any){
    this.id = obj.id;
    this.idUtente = obj.idUtente;
    this.nome = obj.nome;
    this.cognome = obj.cognome;
    this.username = obj.username;
    this.dominio = obj.dominio;
  }
}
