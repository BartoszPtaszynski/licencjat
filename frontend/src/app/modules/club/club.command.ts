import exp from 'node:constants';
import { AuthService } from '../auth/auth.service';

export interface ClubCommand {
  name: string
  formation: string;
}
