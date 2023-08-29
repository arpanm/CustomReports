import dayjs from 'dayjs';
import { IJmdUser } from 'app/shared/model/jmd-user.model';

export interface IJmdSales {
  id?: number;
  prmid?: number | null;
  unisOrder?: number | null;
  unitsDelivered?: number | null;
  unisActivated?: number | null;
  jmdMonth?: number | null;
  jmdYear?: number | null;
  jmddate?: number | null;
  createdBy?: number | null;
  createdOn?: string | null;
  updatedBy?: number | null;
  updatedOn?: string | null;
  retailer?: IJmdUser | null;
}

export const defaultValue: Readonly<IJmdSales> = {};
