# HTMLSTREAM

An implementation of the Medley Interlisp device-independent graphics API for HTML.

## Installation

### Code

Clone this project and copy `HTMLSTREAM.DFASL`, `U-PNG.DFASL`, `BASE64.DFASL` and optionally their Lisp source files,
to a local directory accessible by Medley.

### Font metrics

Clone the project [HtmlStreamFontMetrics](https://github.com/hjellinek/HtmlStreamFontMetrics) into another local directory, say
`/Users/lisphacker/HtmlStreamFontMetrics`.

## Running

Load `HTMLSTREAM.DFASL` into your running Medley system.   Set the variable `*WEB-FONT-DIRECTORY*` to the `fonts`
subdirectory of the `HtmlStreamFontMetrics` directory above, using Interlisp/Tenex syntax.  In our example that would
be `{DSK}/Users/lisphacker/HtmlStreamFontMetrics/fonts` or `{DSK}<Users>lisphacker>HtmlStreamFontMetrics>fonts>`.

Generate HTML output by "printing" to a file with the extension `html`.

You can open the resulting file in any modern Web browser.

## Implementation details

### Fonts

`HTMLSTREAM` generates pages using Google's Noto webfonts.  The font metrics files above
describe the line height, max ascent, max descent, and character widths of all characters in
the intersection of XCCS 3.0.0 and Unicode, segmented by XCCS charset.  We run the
[WebFontMetrics](https://github.com/hjellinek/WebFontMetrics) software (not yet public)
to generate the metrics files.

`HTMLSTREAM` maps common Medley font names to Noto font names using the a-list `*WEB-FONT-MAPPINGS*`.

See the file `HTMLSTREAM.TEdit` for further details.

