export class OvertimeChange {
    constructor(public amount: Number) { }
}

export class Overtime {
    changes: OvertimeChange[];
    
    constructor(public date: Date, public hours: number) { }

    withNegativeHours() {
        return new Overtime(this.date, this.hours * -1);
    }
}