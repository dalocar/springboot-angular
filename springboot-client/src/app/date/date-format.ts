import {NativeDateAdapter} from '@angular/material';
const SUPPORTS_INTL_API = typeof Intl !== 'undefined';

export class DateFormat extends NativeDateAdapter {
  //  useUtcForDisplay = true
  parse(value: any): Date | null {
    console.log('Date formatter');

    const str = value;
    const year = Number(value.substr(0, 4));
    const month = Number(value.substr(4, 2));
    const date = Number(value.substr(6, 2));
    return new Date(year, month, date);

//    const timestamp = typeof value === 'number' ? value : Date.parse(value);
//    return isNaN(timestamp) ? null : new Date(timestamp);
  }
}
