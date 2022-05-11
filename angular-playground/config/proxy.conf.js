module.exports = {
  "/": {
    "secure": false,
    "bypass": (req, res, proxyOptions) => {
//      res.setHeader('Cache-Control', 'no-cache, must-revalidate;')
//      res.setHeader('Report-To', '{"group":"default","max_age":31536000,"endpoints":[{"url":"https://andifalk.report-uri.com/a/d/g"}],"include_subdomains":true};');
//      res.setHeader('NEL', '{"report_to":"default","max_age":31536000,"include_subdomains":true};')
//      res.setHeader("Content-Security-Policy", "default-src 'self'; style-src 'self' 'unsafe-inline'; report-uri https://andifalk.report-uri.com/r/d/csp/enforce;");
//      res.setHeader("Content-Security-Policy", "require-trusted-types-for 'script'; trusted-types angular dompurify default; report-uri https://andifalk.report-uri.com/r/d/csp/enforce;");
//      res.setHeader("Content-Security-Policy", "require-trusted-types-for 'script'; trusted-types angular angular#unsafe-bypass dompurify default mypolicy; report-uri https://andifalk.report-uri.com/r/d/csp/enforce;");
//      res.setHeader("Content-Security-Policy", "default-src 'self'; style-src 'self' 'unsafe-inline'; require-trusted-types-for 'script'; trusted-types angular dompurify default; report-uri https://andifalk.report-uri.com/r/d/csp/enforce;");
    }
  }
};
