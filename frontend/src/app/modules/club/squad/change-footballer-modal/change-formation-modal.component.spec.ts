import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeFootballerModalComponent } from './change-footballer-modal.component';

describe('ChangeFootballerModalComponent', () => {
  let component: ChangeFootballerModalComponent;
  let fixture: ComponentFixture<ChangeFootballerModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChangeFootballerModalComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ChangeFootballerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
