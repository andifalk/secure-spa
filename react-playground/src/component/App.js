import './App.css';
import React from 'react';
import DOMPurify from 'dompurify';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { url: "javascript:alert('xss')", xss: "<b>some text<alert('xss');</b>", value: '' };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    console.log('A name was submitted: ' + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">

        <p>
          <h4>User Input</h4>
          <form onSubmit={this.handleSubmit}>
            <label>
              Name:
              <input type="text" value={this.state.value} onChange={this.handleChange} />
            </label>
            <input type="submit" value="Submit" />
          </form>
        </p>

        <h4>Encoded Text</h4>
        <p>Literal: {this.state.xss}</p>
        <p>User Input: {this.state.value}</p>
        
        <h4>Rendered HTML</h4>
        <p dangerouslySetInnerHTML={{__html: this.state.value}}></p>
        {/*<p dangerouslySetInnerHTML={{__html: DOMPurify.sanitize(this.state.value)}}></p>*/}
        <span dangerouslySetInnerHTML={{__html: this.state.value}}></span>
        <span dangerouslySetInnerHTML={{__html: "<ul><li><i>mytext<iframe src=\"javascript:alert('iframe xss')\"></i></li></ul>"}}></span>
        <span dangerouslySetInnerHTML={{__html: DOMPurify.sanitize("<ul><li><i>mytext<script>alert('xss')</script></i></li></ul>")}}></span>
        {/*<p dangerouslySetInnerHTML={{__html: "<img src=\"nonexistent.png\" onerror=\"alert('This restaurant got voted worst in town!');\" />"}}></p>*/}
        <p dangerouslySetInnerHTML={{__html: DOMPurify.sanitize("<img src=\"nonexistent.png\" onerror=\"alert('This restaurant got voted worst in town!');\" />")}}></p>
        
        <h4>Some URLs</h4>
        <p>
        <a
          className="App-link"
          href={this.state.url}
          target="_blank"
          rel="noopener noreferrer">First Link</a>
        </p>  
        <p>  
        <a
          className="App-link"
          href={this.state.value}
          target="_blank"
          rel="noopener noreferrer">Second Link</a>
        </p>  
      </header>
    </div>
    );
  }  
}


