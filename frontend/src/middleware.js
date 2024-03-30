import { NextResponse } from "next/server"
export function middleware(request) {
    const currentUser = request.cookies.get('currentUser')?.value

    if (!currentUser && (request.nextUrl.pathname !== "/login" && request.nextUrl.pathname !== "/register")) {
      return NextResponse.redirect(new URL('/login', request.url));
    }
    
    if(currentUser && (request.nextUrl.pathname === "/login" && request.nextUrl.pathname === "/register")) {
      return NextResponse.redirect(new URL('/dashboard', request.url));
    }
  }
   
  export const config = {
    matcher: ['/((?!api|_next/static|_next/image|.*\\.png$).*)'],
  }