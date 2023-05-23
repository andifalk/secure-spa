import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import * as Sentry from '@sentry/angular-ivy';
import { BrowserTracing } from '@sentry/tracing';

const activeTransaction = Sentry.getActiveTransaction();
const bootstrapSpan =
  activeTransaction &&
  activeTransaction.startChild({
    description: "platform-browser-dynamic",
    op: "ui.angular.bootstrap",
  });

Sentry.init({
  dsn: "https://c48dd4cdcf4848eba15a5472832c694a@o1234842.ingest.sentry.io/6384507" ,
  integrations: [
    // Registers and configures the Tracing integration,
    // which automatically instruments your application to monitor its
    // performance, including custom Angular routing instrumentation
    new Sentry.BrowserTracing({
      tracePropagationTargets: ["localhost"],
      routingInstrumentation: Sentry.routingInstrumentation,
    }),
    new Sentry.Replay(),
  ],

  // Set tracesSampleRate to 1.0 to capture 100%
  // of transactions for performance monitoring.
  // We recommend adjusting this value in production
  tracesSampleRate: 1.0,
  replaysSessionSampleRate: 0.1,
  replaysOnErrorSampleRate: 1.0,
  debug: false,
});

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
.then(success => console.log(`Bootstrap success`))
.catch(err => console.error(err))
  .finally(() => {
    if (bootstrapSpan) {
      bootstrapSpan.finish();
    }
  });
