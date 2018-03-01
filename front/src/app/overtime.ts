export class OvertimeChange {
  when: Date;

  constructor(public amount: number, when: string) {
    this.when = new Date(when);
  }
}

export class Overtime {
  changes: OvertimeChange[];

  constructor(public date: Date, public hours: number) {
  }

  withNegativeHours() {
    return new Overtime(this.date, this.hours * -1);
  }
}
