//// Kevin Schaefer CST 112
//// Project 2

String title=  "PROJECT TWO";
String news=   "Use 'r' key or click button to reset.";
String author=  "Kevin Schaefer";


float left, right, top, bottom;
float middle;

float cueX,  cueY,  cueDX,  cueDY;
float redX,  redY,  redDX,  redDY;
float yelX,  yelY,  yelDX,  yelDY;
float bluX, bluY, bluDX, bluDY;

float buttonX=380, buttonY=15, buttonW=160, buttonH=40;

//// SETUP:  size and table
void setup() {
  size( 600, 400 );
  left=   50;
  right=  width-50;
  top=    100;
  bottom= height-50;
  middle= left + (right-left) / 2;
  reset();
 }
 
 void reset() {
   cueX=  left + (right-left) / 4;
   cueY=  top + (bottom-top) / 2;
   cueDX= 0;
   cueDY= 0;
   
   // Random positions. 
   redX=  random( middle,right );   redY=  random( top, bottom );
   yelX=  random( middle,right );   yelY=  random( top, bottom );
   bluX=  random( middle,right );   bluY=  random( top, bottom );   
   
   // Random speeds
   redDX=  random( 1,6 );   redDY=  random( -3,3 );
   yelDX=  random( 1,6 );   yelDY=  random( -3,3 );
   bluDX=  random( 1,6 );   bluDY=  random( -3,3 );
   
 }

//// Table, collisions, reset button
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, right, bottom );
  bounce();
  collisions();
  show();
  messages();
  rectMode( CORNER );
  showButton( buttonX, buttonY, buttonW, buttonH );  
  fill(255,255,255);
  text( "CLICK HERE TO RESET", buttonX+buttonW/12, buttonY+buttonH*5/8 );
}

//// Create button
void showButton( float x, float y, float w, float h ) {
  fill(255,175,0);
  rect ( x,y,w,h, 7 );
}

//// Draw table
void table( float left, float top, float right, float bottom ) {
  fill( 100, 250, 100 );    // Green table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( left-20, top-20, right+20, bottom+20 );
  stroke(0);
  strokeWeight(1);
}

//// Bounce & Collisions
void bounce() {
  //// BELOW COMMENTED OUT SECTIONS ARE NOTES FROM CLASS, NOT YET IMPLEMENTED
  //if ( redX<left ) {
  //  redX= left+15;
  //  redDX= +abs(redDX);
  //}
  //if (redX>right) {
  //  redX= right-15;
  //  redDX= -abs(redDX);
  //}
  
  redX += redDX;  if ( redX<left || redX>right ) redDX *= -1;
  redY += redDY;  if ( redY<top || redY>bottom ) redDY *=  -1;
  yelX += yelDX;  if ( yelX<left || yelX>right ) yelDX *= -1;
  yelY += yelDY;  if ( yelY<top || yelY>bottom ) yelDY *=  -1;
  bluX += bluDX;  if ( bluX<left || bluX>right ) bluDX *= -1;
  bluY += bluDY;  if ( bluY<top || bluY>bottom ) bluDY *=  -1;
    //if (cueX<left) { cueX = left+15; cueDX= +abs(cueDX); }
    //if (cueX>right) { cueX = right-15; cueDX= -abs(cueDX); }
    //if (cueY<top) { cueY = top+15; cueDY= +abs(cueDY); }
    //if (cueY<bottom) { cueY = bottom-15; cueDY= -abs(cueDY); }
  //cueX += cueDX;  if ( cueX<left || cueX>right ) cueDX *= -1;
  //cueY += cueDY;  if ( cueY<top || cueY>bottom ) cueDY *=  -1;    
}

//// Button click function & cue draw/movement
void mouseClicked() {
  if ((mouseX) > (buttonX) &&  
     (mouseX) < (buttonX+buttonW) &&
     (mouseY) > (buttonY) &&
     (mouseY) < (buttonY+buttonH)) {
       reset();
     }
  else {
  cueDX= (cueX - mouseX) / 10;
  cueDY= (cueY - mouseY) / 10;
  //strokeWeight( dist(mouseX,mouseY, cueX,cueY) / 30);
  //line(mouseX,mouseY, cueX,cueY);
  }
}

//// Swaps velocities when ball collide
void collisions() {
  float tmp;
  // Swap velocities!
  //if (cueDX != 0) {
    if ( dist( redX,redY, yelX,yelY ) < 30 ) {
      tmp=yelDX;  yelDX=redDX;  redDX=tmp;
      tmp=yelDY;  yelDY=redDY;  redDY=tmp;
    }
    if ( dist( redX,redY, bluX,bluY ) < 30 ){ 
      tmp=bluDX;  bluDX=redDX;  redDX=tmp;
      tmp=bluDY;  bluDY=redDY;  redDY=tmp;
    } 
    if ( dist( yelX,yelY, bluX,bluY ) < 30 ){ 
      tmp=bluDX;  bluDX=yelDX;  yelDX=tmp;
      tmp=bluDY;  bluDY=yelDY;  yelDY=tmp;
    }
    
    //if ( dist( cueX,cueY, bluX,bluY ) < 30 ){ 
      //tmp=bluDX;  bluDX=cueDX;  cueDX=tmp;
      //tmp=bluDY;  bluDY=cueDY;  cueDY=tmp;
    //}
    //if ( dist( cueX,cueY, redX,redY ) < 30 ){ 
      //tmp=redDX;  redDX=cueDX;  cueDX=tmp;
      //tmp=redDY;  redDY=cueDY;  cueDY=tmp;
    //}
    //if ( dist( cueX,cueY, yelX,yelY ) < 30 ){ 
      //tmp=yelDX;  yelDX=cueDX;  cueDX=tmp;
      //tmp=yelDY;  yelDY=cueDY;  cueDY=tmp;
    //}  
  }

//// Display balls & strings
void show() {
  fill( 255,0,0 );    ellipse( redX,redY, 30,30 );
  fill(255,255,255);  text( '1', redX-3, redY+3);
  fill( 255,255,0 );  ellipse( yelX,yelY, 30,30 );
  fill(0,0,0);  text( '2', yelX-3, yelY+3);  
  fill( 0,0,255 );    ellipse( bluX,bluY, 30,30 );
  fill(255,255,255);  text( '3', bluX-3, bluY+3);  
  //fill( 255,255,255 );    ellipse( cueX,cueY, 30,30 );
}
void messages() {
  fill(0);
  text( title, width/8, 30 );
  text( news, width/8, 50 );
  text( author, 10, height-6 );
}


//// Resets when key pressed
void keyPressed() {
  if (key == 'r') {
    reset();
  }
}
