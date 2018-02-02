import { LineInvoice } from './lineinvoice';
export class Invoice {
  id: number;
  year: number;
  date: string;
  irpf: string;
  lines: LineInvoice[];

  constructor(id: number, year: number, date: string, irpf: string, lines: LineInvoice[]) {
    this.id = id;
    this.year = year;
    this.date = date;
    this.irpf = irpf;
    this.lines = lines;
  }
}
