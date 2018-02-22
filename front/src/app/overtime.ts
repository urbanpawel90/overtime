export class Overtime {
    constructor(public date: Date, public hours: number) { }

    withNegativeHours() {
        return new Overtime(this.date, this.hours * -1);
    }
}