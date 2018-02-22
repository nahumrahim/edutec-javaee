import { Component } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IUsuario } from './interface';
import { UsuarioService } from './service';
import { DatePipe } from '@angular/common';

@Component({
  templateUrl: './list.component.html',
})
export class ListUsuariosComponent {
	usuarios: IUsuario[];
	busy = false;

    constructor(private usuarioService: UsuarioService) {}

    ngOnInit() {
    	this.busy = true;
		this.usuarioService.getUsuarios().subscribe((response) => {
				this.busy = false;
				this.usuarios = response;
			}, (err) => {
				this.busy = false;
				console.dir ("Error en la peticion...");
			});
	}

    eliminar(entity: IUsuario) {
		this.busy = true;
		this.usuarioService.delete(entity.id).subscribe((response) => {
			this.busy = false;
		}, (err) => {
			this.busy = false;
		});    	
    }
}
