import * as fromAuthActions from './auth.actions';

export interface State {
    token: string;
    authenticated: boolean;
}

const initialState: State = {
    token: null,
    authenticated: false
}

export function authReducer(state = initialState, action: fromAuthActions.AuthActions) {
    switch (action.type) {
        case (fromAuthActions.SIGNUP):
        case (fromAuthActions.LOGIN):
            return {
                ...state,
                authenticated: true
            };
        case (fromAuthActions.LOGOUT):
            return {
                ...state,
                authenticated: false,
                token: null
            };
        case (fromAuthActions.SET_TOKEN):
            return {
                ...state,
                token: action.payload
            };
        default:
            return state;
    }
}