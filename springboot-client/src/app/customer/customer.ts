import {Invoice} from './invoice';

export class Customer {
  id: number;
  name: string;
  address: string;
  postCode: string;
  city: string;
  province: string;
  cif: string;
  phone: string;
  fax: string;
  showInList: boolean;
  invoices: Invoice[];

  constructor(id: number, name: string, address: string, postcode: string, city: string,
    province: string, cif: string, phone: string, fax: string, showinlist: boolean, invoices: Invoice[]) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.postCode = postcode;
    this.city = city;
    this.province = province;
    this.phone = phone;
    this.fax = fax;
    this.showInList = showinlist;
    this.cif = cif;
    this.invoices = invoices;
  }
}
