import React from 'react';

const CurrentUserContext = React.createContext({
    current: null,
    setCurrent: () => {}
});

export default CurrentUserContext;