import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { trustedTypes } from 'trusted-types';
import * as DOMPurify from 'dompurify';

import * as Sentry from "@sentry/angular";
import { BrowserTracing } from "@sentry/tracing";

if (environment.production) {
  enableProdMode();
}

trustedTypes.createPolicy('default', {
  createHTML: (string, sink) => DOMPurify.sanitize(string, {RETURN_TRUSTED_TYPE: false}),
  createScriptURL: (string, sink) => DOMPurify.sanitize(string, {RETURN_TRUSTED_TYPE: false}),
  createScript: (string, sink) => DOMPurify.sanitize(string, {RETURN_TRUSTED_TYPE: false}),
  createElement: (any: string | Node, sink: any) => DOMPurify.sanitize(any, {RETURN_TRUSTED_TYPE: false})
});

Sentry.init({
  dsn: "https://c48dd4cdcf4848eba15a5472832c694a@o1234842.ingest.sentry.io/6384507",
  integrations: [
    new BrowserTracing({
      tracingOrigins: ["localhost", "https://yourserver.io/api"],
      routingInstrumentation: Sentry.routingInstrumentation,
    }),
  ],
  // Set tracesSampleRate to 1.0 to capture 100%
  // of transactions for performance monitoring.
  // We recommend adjusting this value in production
  tracesSampleRate: 1.0,
});

platformBrowserDynamic().bootstrapModule(AppModule)
.then(success => console.log(`Bootstrap success`))
.catch(err => console.error(err));
