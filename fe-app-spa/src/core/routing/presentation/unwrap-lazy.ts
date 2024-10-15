import { LazyRouteFunction, RouteObject } from 'react-router-dom';

function unwrapLazy(lazyRouteFunction: LazyRouteFunction<RouteObject>): LazyRouteFunction<RouteObject> {
  return async () => {
    const routeObject = await lazyRouteFunction();
    return {
      ...routeObject,
      ...routeObject.Component,
    };
  };
}

export default unwrapLazy;
