
/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
*/


import React from 'react';
import { EffectComposer, Bloom, Vignette, Noise } from '@react-three/postprocessing';
import { BlendFunction } from 'postprocessing';

export const Effects: React.FC = () => {
  return (
    /* Fixed: replaced non-existent 'disableNormalPass' with 'enableNormalPass={false}' as suggested by the error message */
    <EffectComposer enableNormalPass={false} multisampling={0}>
      {/* Tighter bloom to avoid fog: High threshold, moderate radius */}
      <Bloom 
        luminanceThreshold={0.75} 
        mipmapBlur 
        intensity={1.0} 
        radius={0.6}
        levels={8}
      />
      <Noise opacity={0.05} blendFunction={BlendFunction.OVERLAY} />
      <Vignette eskil={false} offset={0.1} darkness={0.5} />
    </EffectComposer>
  );
};
