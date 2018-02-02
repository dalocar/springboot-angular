export class LineInvoice {
  lineNumber: number;
  invoiceId: number;
  invoiceYear: number;
  amount: number;
  description: string;
  price: number;
  vat: number;


  constructor(lineNumber: number, invoiceId: number, invoiceYear: number,
    amount: number, description: string, price: number, vat: number) {
    this.lineNumber = lineNumber;
    this.invoiceId = invoiceId;
    this.invoiceYear = invoiceYear;
    this.amount = amount;
    this.description = description;
    this.price = price;
    this.vat = vat;
  }
}
