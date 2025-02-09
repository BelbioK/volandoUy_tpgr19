export const filterObject = (obj, predicate) => 
    Object.keys(obj)
          .filter( key => predicate(obj[key], key) )
          .reduce( (res, key) => (res[key] = obj[key], res), {} );
