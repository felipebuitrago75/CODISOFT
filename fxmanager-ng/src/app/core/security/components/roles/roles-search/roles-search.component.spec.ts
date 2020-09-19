import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RolesSearchComponent } from './roles-search.component';

describe('RolesSearchComponent', () => {
  let component: RolesSearchComponent;
  let fixture: ComponentFixture<RolesSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RolesSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RolesSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
