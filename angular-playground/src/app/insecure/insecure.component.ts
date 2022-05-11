import { AfterViewInit, Component, ElementRef, OnInit, Sanitizer, ViewChild } from '@angular/core';
import { DomSanitizer, SafeHtml, SafeScript, SafeUrl, SafeValue } from '@angular/platform-browser';
import * as DOMPurify from 'dompurify';

@Component({
  selector: 'app-insecure',
  templateUrl: './insecure.component.html',
  styleUrls: ['./insecure.component.css']
})
export class InsecureComponent implements AfterViewInit {
  @ViewChild('myref') paragraphRef!: ElementRef;
  constructor(private sanitizer: DomSanitizer) {}
  
  unsafeUrl = 'javascript:alert("Hello XSS")'
  unsafeHtml = "<em><script>alert('HTML XSS')</script>Some Italic Text</em>";
  safeResourceUrl:SafeScript = this.sanitizer.bypassSecurityTrustResourceUrl(this.unsafeUrl)
  safeHtml: SafeValue = this.unsafeHtml;
  //safeHtml: SafeValue = this.sanitizer.bypassSecurityTrustHtml(this.unsafeHtml);
  safeUrl: SafeUrl = this.sanitizer.bypassSecurityTrustUrl(this.unsafeUrl);

  ngAfterViewInit(): void {
    let payload: string = "<img src=\"none.jpg\" onerror=\"alert('ElementRef XSS')\"/>";
    this.paragraphRef.nativeElement.innerHTML = payload;
    //this.paragraphRef.nativeElement.innerHTML = DOMPurify.sanitize(payload, {RETURN_TRUSTED_TYPE: true})
  }

  getHtmlSnippet() {
    let safeHtml = `<img src="nonexisting.jpg" onerror="alert('Failed to load image')">`;
    return this.sanitizer.bypassSecurityTrustHtml(safeHtml);
    //return safeHtml;
}

}
