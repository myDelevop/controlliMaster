import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuServiceService {

  constructor() { }

  getMenuList() {
    const menuList: MenuItem[] = [
      {
        group: { code: 'verbali', name: 'Verbali' },
        menus: [
          { code: 'app-regole-checklist', name: 'Regole Checklist' },
          { code: 'app-gestione-template', name: 'Gestione Template' }
        ]
      },
      {
        group: { code: 'controlli', name: 'Controlli' },
        menus: [
          { code: 'assegnamento-controllori', name: 'Assegnamento Controlli' },
        ]
      },
      {
        group: { code: 'impostazioni', name: 'Impostazioni' },
        menus: [
          { code: 'app-utenti-gruppi', name: 'Gestione Utenze' },
          { code: 'app-anagrafiche', name: 'Anagrafiche'}
        ]
      }
    ];
    return menuList;
  }

  getSubMenuName(menuCode: string) {
    const menuList = this.getMenuList();
    for (const group of menuList) {
      for (const menu of group.menus) {
        return ` > ${menu.name}`;
      }
    }
    return '';
  }
}


export interface MenuItem {
  group: Menu;
  menus: Menu[];
}

export interface Menu {
  code: string;
  name: string;
}
