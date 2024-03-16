import { Validators } from "@angular/forms";

export const LOGIN_FORM_VALIDATOR = {
  username: ['', Validators.required],
  password: ['', Validators.required]
}
export interface LoginCommand {
    username: string;
    password: string;
  }
  