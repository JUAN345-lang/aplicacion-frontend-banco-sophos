import { Admin } from "./admin.model";
import { Client } from "./client.model";

export interface Account {
  id?: string;
  creationDate?: string;
  creationUser?: Admin;
  modificationUser?: Admin;
  modificationDate?: string;
  createdDate?: string;
  accountNum?: string;
  balance?: number;
  availableBalance?: number;
  gmfExempt?: boolean;
  client?: Client;
}
