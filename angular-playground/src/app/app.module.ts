import { APP_INITIALIZER, NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Router } from "@angular/router";
import { SecureComponent } from './secure/secure.component';
import { InsecureComponent } from './insecure/insecure.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as Sentry from "@sentry/angular";

@NgModule({
  declarations: [
    AppComponent,
    SecureComponent,
    InsecureComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    {
      provide: ErrorHandler,
      useValue: Sentry.createErrorHandler({
        showDialog: true,
      }),
    },
    {
      provide: Sentry.TraceService,
      deps: [Router],
    },
    {
      provide: APP_INITIALIZER,
      useFactory: () => () => {},
      deps: [Sentry.TraceService],
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
