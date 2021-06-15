import { Activity } from './activity.model';

export interface InvalidRecords {
  message: string;
  invalidRecords: Activity[];
}