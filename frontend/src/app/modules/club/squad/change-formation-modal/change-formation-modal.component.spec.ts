import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeFormationModalComponent } from './change-formation-modal.component';

describe('ChangeFootballerModalComponent', () => {
  let component: ChangeFormationModalComponent;
  let fixture: ComponentFixture<ChangeFormationModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ChangeFormationModalComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ChangeFormationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
