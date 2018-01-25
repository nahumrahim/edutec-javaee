import { BrowserModule }                       from '@angular/platform-browser';
import { NgModule }                            from '@angular/core';
import { FormsModule, ReactiveFormsModule  }   from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule }             from '@angular/platform-browser/animations';
import { AppComponent }                        from './app.component';
import { MaterialModule }                      from './material.module';
import { FlexLayoutModule }                    from '@angular/flex-layout';
import { NgProgressModule }                    from 'ngx-progressbar';
import { ModalDialogModule }                   from './shared/dialog/module';
import { AppRoutingModule }                    from './app-routing.module';
import { DataGridComponent }                   from './shared/datagrid/component';
import { ListParametrosComponent }             from './shared/parametros/list.component';
import { HomeComponent }                       from './home/component';

// Providers
import { RequestResponseInterceptor }    from './shared/interceptors/request.response.interceptor';
import { ParametroService }              from './shared/parametros/service';


@NgModule({
    declarations: [
        AppComponent,
        DataGridComponent,
        ListParametrosComponent,
        HomeComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        FormsModule,
        ReactiveFormsModule ,
        HttpClientModule,
        MaterialModule,
        FlexLayoutModule,
        NgProgressModule,
        ModalDialogModule
    ],

    providers: [
        ParametroService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: RequestResponseInterceptor,
            multi: true,
        },        
    ],
    entryComponents: [
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
