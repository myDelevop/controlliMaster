import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
  pure: false
})
export class FilterPipe implements PipeTransform {

/*  transform(arr: any[], prop1: string, value: string ): any {
    if (arr) {
      if (!value) {
        return arr;
      } else {
        let arr2: Azienda[] = [];

        arr2 = arr.map(x => x.azienda);

        return arr.filter(obj => Object.keys(obj).some(field => {
          if (typeof obj[field] === 'string') {
            return !obj[field].toLowerCase().includes(value.toLocaleLowerCase()
            );
          } /*else {
          return true;
}
        }));
      }
    } else {
      return [];
    }
  } */

  transform(arr: any[], prop1: string, prop2: string, value: string ): any {
    if (arr) {
      if (!value) {
        return arr;
      } else {

        return arr.filter(obj => obj[prop1][prop2].includes(value));
      }
    } else {
      return [];
    }
  }

}
