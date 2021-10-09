import { AfterViewInit, Component, ElementRef, OnInit, Sanitizer, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DomSanitizer, SafeHtml, SafeScript, SafeUrl, SafeValue } from '@angular/platform-browser';

@Component({
  selector: 'app-insecure',
  templateUrl: './insecure.component.html',
  styleUrls: ['./insecure.component.css']
})
export class InsecureComponent {

  constructor(private sanitizer: DomSanitizer, private elementRef: ElementRef) {
    const s = document.createElement('script');
    s.type = 'text/javascript';
    s.textContent = 'alert("ElementRef XSS")';
    this.elementRef.nativeElement.appendChild(s);
   }

  unsafeUrl = 'javascript:alert("iFrame XSS")'
  unsafeHtml = "<em><script>alert('XSS')</script>Some Italic Text</em>";
  safeResourceUrl:SafeScript = this.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl)
  safeHtml: SafeValue = this.sanitizer.bypassSecurityTrustHtml(this.unsafeHtml);
  safeUrl: SafeUrl = this.sanitizer.bypassSecurityTrustUrl(this.unsafeUrl)

getHtmlSnippet() {
    let safeHtml = `<img src="nonexisting.jpg" onerror="alert('Failed to load image')">`;
    return this.sanitizer.bypassSecurityTrustHtml(safeHtml);
}

}
