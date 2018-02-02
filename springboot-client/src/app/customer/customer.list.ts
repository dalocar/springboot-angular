import {Customer} from './customer';

export class CustomerList {
  customers: Customer[];
  total: number;

  constructor(total: number, customers: Customer[]) {
    this.total = total;
    this.customers = customers;
  }
}
