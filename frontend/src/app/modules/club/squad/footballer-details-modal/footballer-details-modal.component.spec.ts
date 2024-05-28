import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootballerDetailsModalComponent } from './footballer-details-modal.component';

describe('FootballerDetailsModalComponent', () => {
  let component: FootballerDetailsModalComponent;
  let fixture: ComponentFixture<FootballerDetailsModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FootballerDetailsModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FootballerDetailsModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
