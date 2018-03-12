import {Overtime} from './overtime';

describe('Overtime', () => {
  const overtime = new Overtime(new Date(Date.now()), 3.5);
  it('negateHours', () => {
    expect(overtime.withNegativeHours().hours).toBe(-3.5);
  });
});
