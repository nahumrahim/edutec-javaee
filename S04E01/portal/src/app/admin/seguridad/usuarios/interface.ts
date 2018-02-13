import { IRol } from "../roles/interface";
import { IEntity } from "../../../shared/interface/entity";

export interface IUsuario extends IEntity { 
	codigo: string
	nombre: string
	email: string
	telefono: string
	password?: string
	rol: IRol
	idRol?: number
}