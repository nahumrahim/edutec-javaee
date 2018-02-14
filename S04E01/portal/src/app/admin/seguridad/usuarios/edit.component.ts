import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractDialogContent } from '../../../shared/dialog/abstract.dialog.content';
import { ModalDialogService } from '../../../shared/dialog/service';
import { IUsuario } from './interface';
import { IRol } from '../roles/interface';
import { UsuarioService } from './service';
import { RolService } from '../roles/service';
import { Observable } from 'rxjs/Rx';

@Component({
  templateUrl: './edit.component.html',
})
export class EditUsuarioComponent extends AbstractDialogContent<IUsuario, UsuarioService> {
    roles: IRol[];
    
    constructor(protected router: Router,
        protected route: ActivatedRoute, 
        protected service: UsuarioService,      
        protected dialogService: ModalDialogService,
        private rolService: RolService) {
        super(router, route, service, dialogService);
        this.redirectUrl = '/admin/usuarios';
    }
    
    ngOnInit() {
        this.readOnly = false
        this.initEntity()
        this.fetchEntity()
        this.rolService
            .getEntities()
            .subscribe((response) => {
                this.roles = response;
            }, (err) => {
            });
    }

    initEntity() {
        this.entity = {
            id: null,
            codigo: null,
            password: "",
            email: "",
            nombre: "",
            rol: {
                id: null,
                nombre: "",
                descripcion: ""
            },
            telefono: ""
        }       
    }
    
    fetchEntity() {
        this.route.params.subscribe(params => {
            this.id = +params['id'];

            if (this.id && this.id != null) {
                this.busy = true;
                this.service
                    .getEntity(this.id)
                    .subscribe((response) => {
                        this.busy = false;
                        this.entity = response;
                    }, (err) => {
                        this.busy = false;
                    });
            }
        });
    }
    
    public save() {
        let clonedEntity = Object.assign({}, this.entity)
        console.dir (clonedEntity)
        clonedEntity.idRol = this.entity.rol.id
        delete clonedEntity.rol
        super.save(clonedEntity)
    }

}
