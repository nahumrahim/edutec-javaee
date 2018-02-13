import { Component } from '@angular/core';
import { Router } from '@angular/router'
import { IUsuario } from './interface';
import { UsuarioService } from './service';
import { IDatagridOptions } from '../../../shared/datagrid/interface'
import { DataGridComponent } from '../../../shared/datagrid/component';
import { ModalDialogService } from '../../../shared/dialog/service'

@Component({
    template: `
        <div>
            <datagrid [options]="options" [service]="service"></datagrid>
        </div>
    `
})
export class ListUsuariosComponent extends DataGridComponent {
    options:  IDatagridOptions;

    constructor(
        public service: UsuarioService, 
        protected router: Router,
        protected dialogService: ModalDialogService,
        ) {
        super(dialogService, router )
    }

    ngOnInit() {
        let esLdapCatalog = []
        esLdapCatalog[0] = "Local"
        esLdapCatalog[1] = "De dominio"

        this.options = {
            pageSize: 20, 
            title: "Usuarios",
            baseUrl: "/admin/usuarios",
            columns: [
                { name: "nombre", caption: "Nombre", sortDirection: 0, width: "auto"},
                { name: "email", caption: "Email", sortDirection: 0, width: "auto" },
                { name: "rol.nombre", caption: "Rol", sortDirection: 0, width: "auto" }
            ],
            showHeader: true,
            editable: true
        }
        super.ngOnInit()
    }
    
}
