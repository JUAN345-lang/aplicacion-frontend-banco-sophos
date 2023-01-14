import { Admin } from "./admin.model";

export interface Client {
  id?: string;
  creationDate?: string;
  name?: string;
  lastName?: string;
  phone?: string,
  birthday?: string;
  email?: string;
  identification?: string;
  typeIdentification?: string;
  creationUser?: Admin;
  modificationUser?: Admin;
}
